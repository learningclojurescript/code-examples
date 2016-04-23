(ns logic-demo.core
  (:require [cljs.core.logic :as l]))

(l/run* [q]
  (l/membero q [1 2 3 4])
  (l/membero q [3 4 5 6]))

(l/run* [q]
  (l/== q 5))

(l/run* [q]
  (l/fresh [a]
    (l/== a q)
    (l/membero a [:animal :mineral :vegetable])
    (l/membero q [:animal :plant :fungi :bacteria])))

(l/run* [q]
  (l/fresh [a]
    (l/== a q)
    (l/membero a [1 2 3 4])
    (l/membero q [3 4 5 6])))

(l/run* [q]
  (l/conde
    ((l/== q 5) l/succeed)
    (l/succeed l/succeed)))

(l/run* [q]
  (l/== q l/fail))

(l/run* [q]
  (l/conde
    (l/succeed l/succeed)
    (l/succeed l/fail)
    (l/fail l/succeed)))

(l/run* [q]
  (l/conde
    ((l/== :apricot q) (l/== :scoop q))
    ((l/== :banana q) l/succeed)))

(l/run* [q]
  (l/conde
    ((l/membero q [:a :b :c]) (l/membero q [:b :c :d]))
    ((l/== :banana q) l/succeed)))

(l/run* [q]
  (l/conso 0 [1 2] q))

(l/run* [q]
  (l/conso q [1 2] [0 1 2]))

(l/run* [q]
  (l/conso 0 q [0 1 2]))

(l/run* [q]
  (l/conso 0 [1 q] [0 1 2]))

(l/run* [q]
  (l/resto [1 2 3 4] [2 q 4]))

(l/run* [q]
  (l/membero 1 [q 2 3]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
