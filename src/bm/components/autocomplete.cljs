(ns bm.components.autocomplete
  (:require [shadow.cljs.modern :refer (defclass)]
            [shadow.resource :refer (inline)]
            [bm.web-components.utils :refer
             (BMElement tag append-child event-listener register-custom-element)]))

(defclass Autocomplete
  (extends BMElement)
  (field input)
  (field worker)

  (constructor [this]
               (super)
               (let [placeholder "🔎  Type in a product or service keyword."
                     autocomplete (tag :input {:placeholder placeholder})]
                 (append-child this (tag :style (inline "./autocomplete.css")))
                 (append-child this autocomplete)
                 (set! input autocomplete)
                 (event-listener autocomplete :input (.handleInput this))))

  Object
  (connectedCallback [this]
                     (set! worker (js* "new Worker('/js/workers/autocomplete.js', {type: 'module'})"))
                     (event-listener worker :message (.handleMessage this)))

  (disconnectedCallback [this] (.terminate worker))

  (handleInput [this] (fn [event] (.postData worker (.value this))))

  (handleMessage [this] (fn [event] (.buildMenu this (.-data event))))

  (buildMenu [this items] (js/console.log :menu items :count (count items)))

  (value [this] (.-value input))

  (setValue [this value] (set! input -value value)))

(register-custom-element :bm-autocomplete Autocomplete)
