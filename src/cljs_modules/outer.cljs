(ns cljs-modules.outer
  (:require [om.core :as om]
            [om.dom :as dom :include-macros true]))

(defn outer-component [app owner opts]
  (reify om/IRender
    (render [_]
      (dom/div #js {} nil
               (dom/h1 #js {} "Hello from Outer!")
               (dom/a #js {:href "/app"} "inner")))))
