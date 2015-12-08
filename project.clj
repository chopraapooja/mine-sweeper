(defproject scaffold "0.1.0-SNAPSHOT"
	:description "Boot Camp Assignment"
	:dependencies [[org.clojure/clojure "1.7.0"]]
	:profiles {:dev {:dependencies [[speclj "3.3.0"]]}}
	:plugins [[speclj "3.3.0"]]
	:test-paths ["spec"])