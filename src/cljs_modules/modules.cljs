(ns cljs-modules.modules
  (:require [cljs.core.async :as a]
            [cljs.core.async :as a]
            [goog.module :as module]
            [goog.module.ModuleManager :as module-manager]
            [goog.module.ModuleLoader]
            [om.core :as om]))

(def modules? true) ;; true if serving modules

(def modules
  ;; "The map of id/uris pairs for each module."
  #js {"inner" "/js/compiled/inner.js"
       "outer" "/js/compiled/outer.js"})

(def module-info
  ;; "An object that contains a mapping from module id (String) to list of required module ids (Array)."
  #js {"inner" []
       "outer" []})

(def modules-loaded {"inner" (a/chan)
                     "outer" (a/chan)})

(def manager (module-manager/getInstance))
(def loader (goog.module.ModuleLoader.))

(.setLoader manager loader)
(.setAllModuleInfo manager module-info)
(.setModuleUris manager modules)

(defn loaded? [id]
  (if-let [module (.getModuleInfo manager id)]
    (.isLoaded module)
    false))

(defn require-module
  "Loads module if necessary. Returns a channel that will be closed once loaded"
  [app id]
  (let [chan (get modules-loaded id)]
    (assert chan)
    (if (not (loaded? id))
      (do
        (println "requiring" id)
        (.execOnLoad manager id (fn []
                                  (om/update! app [:loaded id] true)
                                  (-> modules-loaded (get id) (a/close!)))))
      (a/close! chan))
    chan))

(defn set-loaded!
  "Mark a module as loaded"
  [id]
  (println "module" id "loaded!" id)
  (-> goog.module.ModuleManager .getInstance (.setLoaded id))
  (-> modules-loaded (get id) (a/close!)))

(defn get-load-chan [id]
  {:pre [(string? id)]
   :post [%]}
  (get modules-loaded id))
