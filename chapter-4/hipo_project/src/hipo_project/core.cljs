(ns hipo-project.core
  (:require [hipo.core :as hipo]))

(defn create-menu-v [items]
   [:ul#my-menu
    (for [x items]
      [(keyword (str "li#" x) ) x])])
       
(def menu (hipo/create (create-menu-v ["it1" "it2" "it3"])))

(defn add-menu!
  []
  (.appendChild js/document.body menu))

(defn reconcile-new-menu! []
  (hipo/reconciliate! menu (create-menu-v ["new it1" "new it2" "new it3"])))
