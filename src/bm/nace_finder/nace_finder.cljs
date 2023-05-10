(ns bm.nace-finder.nace-finder
  (:require [shadow.cljs.modern :refer (defclass)]))

(defclass NaceFinder
  (extends js/HTMLElement)

  (constructor [this]
               (super)
               (let [shadow (.attachShadow this #js{:mode "open"})]
                 (set! shadow -innerHTML "<h1>Nace finder</h1>")))


  Object
  (connectedCallback [this]
                     (js/console.log "bm-nace-finder" this)))

(when-not (js/window.customElements.get "bm-nace-finder")
  (js/window.customElements.define "bm-nace-finder" NaceFinder))
