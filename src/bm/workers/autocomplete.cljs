;(ns bm.workers.autocomplete)

;(def data (edn/read-string (inline "./autocomplete.edn")))
;(js/console.log data)

(let [response (js/await (js/fetch "/data/autocomplete.json"))]
  (js/console.log :response response))

(defn- format-l4-item [item]
  {:label (:label item) :items []}) ; TODO: Match 6 item.

(defn- l4-item-matches [item]
  true)

(defn- match-l4-item [item]
  (when (l4-item-matches item) (format-l4-item item)))

(defn- filter-items [search-term]
  (filter identity
          (map (fn [l4-item] (match-l4-item l4-item)) data)))

; ^js
(defn- onMessage [event]
  (let [search-term (.-data event)]
    (js/console.log :search-term search-term)
    (js/postData (filter-items search-term))))
