(ns schema-demo.demographics
  (:require [schema.core :as s :include-macros true]))

(def city
  "A schema to describe a city"
  {:type s/Keyword
   :name s/Str
   :population s/Int})

(def x 5)

#_(def state
  "A schema to describe a state"
  {:type s/Keyword
   :name s/Str
   :population s/Int
   :capital city})

(def state
  "A schema to describe a state"
  {:type s/Keyword
   :name s/Str
   :population s/Int
   :capital {:type s/Keyword
             :name s/Str
             :population s/Int}})

(def form
  [(s/one s/Str "s")
   (s/optional s/Keyword "k")
   s/Int])
