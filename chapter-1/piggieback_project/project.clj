(defproject piggieback_project "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-cljsbuild "1.1.3"]]
  :cljsbuild {
              :builds [{:source-paths ["src"]
                        :compiler {:main piggieback-project.core
                                   :output-to "out/main.js"
                                   :output-dir "out"
                                   :optimizations :none}}]}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.8.51"]
                 [weasel "0.7.0" :exclusions [org.clojure/clojurescript]]]
  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.2.1"]
                                  ;[org.clojure/tools.nrepl "0.2.10"]
                                  ]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}})
