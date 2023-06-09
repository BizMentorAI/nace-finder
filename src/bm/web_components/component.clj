(ns bm.web-components.component
  (:require [shadow.cljs.modern :refer (defclass)]))

(defmacro component [tag class-name & opts]
  (let [class-name (symbol (name class-name))
        macro
        `(defclass ~class-name
           ~(list 'extends 'js/HTMLElement)
           ~(list 'constructor ['this] '(super))
           ;(~'constructor [~'this]
            ;(~'super)
            ;; (let [shadow-root# (.attachShadow ~'this (clj->js {:mode "open"}))]
            ;;                             ; if styles add styles.
            ;;   (.appendChild ~'this (.render ~'this shadow-root#)))
            ;)
           )]
    (println "----")
    (prn macro)
    (println "----")
    macro))

;; (ns bm.web-components.component
;;   (:require [shadow.cljs.modern :refer (defclass)]))

;; (defmacro component [tag class-name & opts]
;;   `(defclass ~class-name
;;      (~'extends js/HTMLElement)
;;      (~'constructor [~'this]
;;       (~'super))))


;; (component :bm-autocomplete :Autocomplete
;;            :styles styles
;;            :render (fn [shadow-root]))

;; (value [this] (.-value input))
;; (setValue [this value] (set! input -value value))
; Actually this might require the use of getters above ^.
; (js/Object.defineProperty Autocomplete.prototype "value" #js {:get (fn [] ...)})
;
; Although maybe we want attrs for changes coming from the outside only.
;(set! (.-observedAttributes Autocomplete) #js ["value"])
