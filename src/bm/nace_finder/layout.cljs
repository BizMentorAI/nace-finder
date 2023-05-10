(ns bm.nace-finder.layout
  (:require-macros [hiccups.core :as hiccups :refer (html)])
  (:require [shadow.cljs.modern :refer (defclass)]
            [hiccups.runtime :as hiccupsrt]
            [bm.nace-finder.layout.header]
            [bm.nace-finder.layout.footer]))

(def styles "
  html {
    width: 100%;
    height: 100%;
  }

  body {
    margin: 0;
    background: yellow;
  }
")

(defclass Layout
  (extends js/HTMLElement)

  (constructor [this]
               (super)
               (set! this -innerHTML
                     (str
                      (html [:style styles])
                      (html [:bm-header])
                      (html [:slot])
                      (html [:bm-footer]))))

  Object
  (connectedCallback [this]
                     (js/console.log "bm-layout" this)))

(when-not (js/window.customElements.get "bm-layout")
  (js/window.customElements.define "bm-layout" Layout))
