(ns cljs-modules.core
  (:require [accountant.core :as accountant]
            [bidi.bidi :as bidi]
            [om.core :as om]
            [om.dom :as dom :include-macros true]
            [cljs-modules.modules :as modules]
            [cljs-modules.render :as render]))

(enable-console-print!)

(defonce app-state (atom {:active-component :outer/outer}))

(defn nav-handler [cursor path]
  (om/update! cursor [:active-component] (:handler (bidi/match-route render/routes path))))

(defn main []
  (om/root
   render/render
   app-state
   {:target (. js/document (getElementById "app"))})

  (let [cursor (om/root-cursor app-state)]
    (accountant/configure-navigation! {:nav-handler (fn [path]
                                                      (nav-handler cursor path))
                                       :path-exists? (fn [path]
                                                       (boolean (bidi/match-route render/routes path)))})
    (accountant/dispatch-current!)))

(main)
