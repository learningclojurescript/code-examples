(ns cljs-modules.inner
  (:require [om.core :as om]
            [om.dom :as dom :include-macros true]))

(defn inner-component [app owner opts]
  (reify om/IRender
    (render [_]
      (dom/div #js {} nil
               (dom/h1 #js {} "Hello from Inner!")
               (dom/a #js {:href "/"} "outer")))))
