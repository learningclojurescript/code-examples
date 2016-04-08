(ns cljs-modules.inner
  (:require [om.core :as om]
            [om.dom :as dom :include-macros true]
            [cljs-modules.render :as render]
            [cljs-modules.modules :as modules]))

(defn inner-component [app owner opts]
  (reify om/IRender
    (render [_]
      (dom/div #js {} nil
               (dom/h1 #js {} "Hello from Inner!")
               (dom/a #js {:href "/"} "outer")))))

(defmethod render/active-component :inner/inner [_]
  inner-component)

(modules/set-loaded! "inner")
