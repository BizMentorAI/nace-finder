(ns bm.nace-finder.layout.footer
  (:require-macros [hiccups.core :as hiccups :refer (html)])
  (:require [shadow.cljs.modern :refer (defclass)]
            [hiccups.runtime :as hiccupsrt]))

(def styles "
")

(defclass Footer
  (extends js/HTMLElement)

  (constructor [this]
               (super)
               (set! this -innerHTML
                     (str
                      (html [:style styles])
                      (html [:footer]))))

  Object
  (connectedCallback [this]
                     (js/console.log "bm-footer" this)))

(when-not (js/window.customElements.get "bm-footer")
  (js/window.customElements.define "bm-footer" Footer))
