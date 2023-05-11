(ns bm.components.autocomplete
  (:require [shadow.cljs.modern :refer (defclass)]
            [bm.web-components.utils :refer
             (BMElement shadow-root tag append-child event-listener register-custom-element)]))

(def styles "
  input {
    padding: 10px 15px;
    font-size: 14pt;
    border-radius: 12px;
    border: 1px solid darkgrey;
  }
")

(defclass Autocomplete
  (extends BMElement)
  (field input)

  (constructor [this]
               (super)
               (let [autocomplete (tag :input)]
                 (append-child this (tag :style styles))
                 (append-child this autocomplete)
                 (set! input autocomplete)
                 (event-listener autocomplete :input (.handleInput this))))

  Object
  (handleInput [this]
               (fn [event]
                 (js/console.log :handleInput {:value (.value this)})
                 (js/console.log event)))

  (value [this] (.-value input))
  (setValue [this value] (set! input -value value)))

; Actually this might require the use of getters above ^.
; (js/Object.defineProperty Autocomplete.prototype "value" #js {:get (fn [] ...)})
(set! (.-observedAttributes Autocomplete) #js ["value"])

(register-custom-element :bm-autocomplete Autocomplete)
