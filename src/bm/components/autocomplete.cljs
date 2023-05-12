(ns bm.components.autocomplete
  (:require [shadow.cljs.modern :refer (defclass)]
            [shadow.resource :refer (inline)]
            [clojure.edn :as edn]
            [bm.web-components.utils :refer
             (BMElement tag append-child event-listener register-custom-element)]))

(def data (edn/read-string (inline "./autocomplete.edn")))
(js/console.log data)

(defclass Autocomplete
  (extends BMElement)
  (field input)

  (constructor [this]
               (super)
               (let [placeholder "🔎  Type in a product or service keyword."
                     autocomplete (tag :input {:placeholder placeholder})]
                 (append-child this (tag :style (inline "./autocomplete.css")))
                 (append-child this autocomplete)
                 (set! input autocomplete)
                 (event-listener autocomplete :input (.handleInput this))))

  Object
  (handleInput [this]
               (fn [event]
                 (.buildMenu this (.filterItems this data (.value this)))))

  (filterItems [this data search-term]
               (filter identity
                       (map (fn [l4-item]
                              (.matchL4Item this l4-item))
                            data)))

  ; Either return nil or
  ; {"L4 title": [L6 item I, L6 item II...]}
  (matchL4Item [this item]
               true)

  (buildMenu [this items]
             (js/console.log :menu items :count (count items)))

  (value [this] (.-value input))

  (setValue [this value] (set! input -value value)))

(register-custom-element :bm-autocomplete Autocomplete)
