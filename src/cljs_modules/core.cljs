(ns cljs-modules.core
  (:require [accountant.core :as accountant]
            [bidi.bidi :as bidi]
            [om.core :as om]
            [om.dom :as dom :include-macros true]
            [cljs-modules.inner :as inner]
            [cljs-modules.outer :as outer]))

(defonce app-state (atom {:text "Hello world!"}))

(def routes ["/" {"" outer/outer-component
                  "app" inner/inner-component}])

(defn nav-handler [cursor path]
  (om/update! cursor [:active-component] (:handler (bidi/match-route routes path))))

(defn renderer [app owner opts]
  (reify
    om/IRender
    (render [_]
      (dom/div
       nil
       (if-let [c (:active-component app)]
         (om/build c app {})
         (dom/p nil "no active component"))))))

(defn main []
  (om/root
   renderer
   app-state
   {:target (. js/document (getElementById "app"))})

  (let [cursor (om/root-cursor app-state)]
    (accountant/configure-navigation! {:nav-handler (fn [path]
                                                      (nav-handler cursor path))
                                       :path-exists? (fn [path]
                                                       (boolean (bidi/match-route routes path)))})
    (accountant/dispatch-current!)))

(main)
