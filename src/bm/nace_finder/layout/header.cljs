(ns bm.nace-finder.layout.header
  (:require-macros [hiccups.core :as hiccups :refer (html)])
  (:require [shadow.cljs.modern :refer (defclass)]
            [hiccups.runtime :as hiccupsrt]))

(def styles "
")

(defclass Header
  (extends js/HTMLElement)

  (constructor [this]
               (super)
               (set! this -innerHTML
                     (str
                      (html [:style styles])
                      (html [:header]))))

  Object
  (connectedCallback [this]
                     (js/console.log "bm-header" this)))

(when-not (js/window.customElements.get "bm-header")
  (js/window.customElements.define "bm-header" Header))
