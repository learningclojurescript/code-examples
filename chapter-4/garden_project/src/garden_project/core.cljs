(ns garden-project.core
  (:require [garden.core :refer [css]]
            [goog.style]))


(defn modify-css!
  []
  (goog.style/installStyles (css
                             [:div {:border-style "solid"}
                              [:&:hover {:border-style "dashed"}]
                              [:p {:background-color "cyan"}]])))



