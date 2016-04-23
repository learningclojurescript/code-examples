(ns testing.async-test
  (:require [ajax.core :refer [GET]]
            [cljs.test :refer-macros [deftest is async]]))

(deftest test-async
  (async done
    (GET "http://www.google.com"
         ;; will always fail from PhantomJS because
         ;; `Access-Control-Allow-Origin` won't allow
         ;; our headless browser to make requests to Google.
         {:error-handler
          (fn [res]
            (is (= (:status-text res) "Request failed."))
            (println "Test finished!")
            (done))})))
