(ns cljs-modules.modules
  (:require [goog.module :as module]
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
  [app id]
  (if (not (loaded? id))
    (do
      (println "requiring" id)
      (om/update! app [:loading id] true)
      (.execOnLoad manager id (fn []
                                (om/update! app [:loaded id] true)
                                (om/update! app [:loading id] false))))))

(defn set-loaded!
  "Mark a module as loaded"
  [id]
  (println "module" id "loaded!" id)
  (-> goog.module.ModuleManager .getInstance (.setLoaded id)))
