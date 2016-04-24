(ns cljs-modules.render
  (:require [om.core :as om]
            [om.dom :as dom]))

(def routes ["/" {"" :outer
                  "app" :inner}])

(defmulti active-component identity)

(defmethod active-component :default [_]
  nil)

(defn render [app owner opts]
  (reify
    om/IRender
    (render [_]
      (dom/div nil
               (if-let [c (active-component (:active-component app))]
                 (om/build c app {})
                 (dom/p nil "no active component"))))))
