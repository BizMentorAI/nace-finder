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

  (matchL4Item [this item]
               (when (.l4ItemMatches this item) (.formatL4Item this item)))

  (l4ItemMatches [this item]
                 true)

  (formatL4Item [this item]
                {:label (:label item) :items []}) ; TODO: Match 6 item.

  (buildMenu [this items]
             (js/console.log :menu items :count (count items)))

  (value [this] (.-value input))

  (setValue [this value] (set! input -value value)))

(register-custom-element :bm-autocomplete Autocomplete)

; TODO: inlining this got us to 2.5MB from 100kb
; Not sure why, the EDN only has 484K.
; We can remove :level for L4 items.
