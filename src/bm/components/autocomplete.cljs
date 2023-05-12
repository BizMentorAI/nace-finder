(ns bm.components.autocomplete
  (:require [shadow.cljs.modern :refer (defclass)]
            [shadow.resource :refer (inline)]
            [bm.web-components.utils :refer
             (BMElement tag append-child event-listener register-custom-element)]))

(defclass Autocomplete
  (extends BMElement)
  (field input)

  (constructor [this]
               (super)
               (let [autocomplete (tag :input {:placeholder "🔎  Type in a product or service keyword."})]
                 (append-child this (tag :style (inline "./autocomplete.css")))
                 (append-child this autocomplete)
                 (set! input autocomplete)
                 (event-listener autocomplete :input (.handleInput this))))

  Object
  (handleInput [this]
               (fn [event]
                 (js/console.log :handleInput {:value (.value this)})
                 (js/console.log event)
                 ; TODO: filter options.
                 ; We do not need event.target by the way, we can just
                 ; refer the input field directly.
                 ))

  (value [this] (.-value input))
  (setValue [this value] (set! input -value value)))

(register-custom-element :bm-autocomplete Autocomplete)
