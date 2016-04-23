(ns match-demo.core
  (:require [cljs.core.match :refer-macros [match]]))

(defprotocol foo
  (bar [this]))

(deftype baz []
  Object
  (bar [this] 5))

;; basic
(let [v [:a :a :b]]
  (match v
    [_ :a :a] 1
    [:a :b _ ] 2
    [_ _ :a] 3
    [_ _ :b] 4
    :else 5))

;; local bindings
(match [:x :y]
       [:y a] a
       [b :y] b
       :else nil)

;; vectors
(match [[:a :b :c]]
       [[:a :c _]] 1
       [[:b _ :a]] 2
       [[_ :b :c]] 3
       :else nil)

;; maps
(let [v {:x 1 :y 1}]
 (match [v]
        [{:x _ :y 2}] 1
        [{:x 1 :y 1 :z _}] 2
        :else "no match found"))

(let [v {:x 1 :y 1}]
  (match [v]
         [({:x 1} :only [:x])] true
         :else false))

(let [v {:x {:y :z}}]
  (match [v]
         [{:x {:y k}}] k
         :else nil))

;; sequential types
(let [x [1 2 nil nil nil]]
  (match [x]
    [([1 2] :seq)] :a1
    [([1 2 nil nil nil] :seq)] :a2
    :else nil))

;; rest patterns
(let [x [1 2 nil nil nil]]
  (match [x]
    [([1 2] :seq)] :a1
    [([1 2 & r] :seq)] :a2
    :else nil))

;; or
(let [x ["a" "b" "c"]]
  (match x
         ["a" "d" "c"] :a1
         ["z" "d" "c"] :a1
         ["x" "b" "c"] :a2
         ["a" _ "d"] :a3
         ["a" _ "c"] :a3
         ["z" _ "d"] :a3
         ["z" _ "c"] :a3
         :else nil))

;; guards
(let [x [1 3]]
  (match x
         [(_ :guard odd?) _] :a1
         [(_ :guard even?) (_ :guard even?)] :a2
         :else nil))

;; general function application
(let [x [1 3]]
  (match x
         [(2 :<< println) 3] :a1
         [1 (2 :<< dec)] :a2
         :else nil))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
