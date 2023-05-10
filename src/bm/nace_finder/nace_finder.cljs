(ns bm.nace-finder.nace-finder
  (:require [shadow.cljs.modern :refer (defclass)]))

(defclass NaceFinder
  (extends js/HTMLElement)

  (constructor [this]
    (super))

  Object
  (connectedCallback [this]
    (js/console.log "bm-nace-finder" this)))

(js/window.customElements.define "bm-nace-layout" NaceFinder)
