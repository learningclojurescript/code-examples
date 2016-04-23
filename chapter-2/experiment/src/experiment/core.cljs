(ns experiment.core
  (:require [experiment.consumers :as consumers]
            [experiment.async]
            [experiment.zip]))

(enable-console-print!)

(defn render
  []
  (.render (js/treeact)))

(render)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
