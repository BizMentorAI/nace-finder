#!/usr/bin/env bb
(defn exit-with-error [message]
  (throw (ex-info
          (str \u001b "[31m" "Error: " \u001b "[0m" message)
          {:babashka/exit 1})))

(when-not (= (count *command-line-args*) 1)
  (exit-with-error "only 1 argument (input file path) expected."))

(def input-file-path (first *command-line-args*))

; 4 is the industry level, 6 would be the product level.
(defn select-row [row]
  (= (second row) "4"))

(defn process-row [row]
  {:id (first row) :label (nth row 4)})

(defn process-data [data]
  (mapv process-row (filter select-row data)))

(with-open [reader (io/reader input-file-path)]
  (doall
   (csv/write-csv *out* (prn (process-data (csv/read-csv reader))))))
