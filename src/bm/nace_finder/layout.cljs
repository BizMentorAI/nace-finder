(ns bm.nace-finder.layout
  (:require [shadow.cljs.modern :refer (defclass)]))

(defclass Layout
  (extends js/HTMLElement)

  (constructor [this]
    (super))

  Object
  (connectedCallback [this]
    (js/console.log "bm-layout" this)))

(js/window.customElements.define "bm-layout" Layout)
