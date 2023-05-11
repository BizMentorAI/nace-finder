(ns bm.nace-finder.layout.header
  (:require-macros [hiccups.core :as hiccups :refer (html)])
  (:require [shadow.cljs.modern :refer (defclass)]
            [hiccups.runtime :as hiccupsrt]))

(def styles "
  header {
    background: red;
    padding: 20px 0;
    text-align: center;
  }

  header h1 {
    margin-top: 0;
  }
")

(defclass Header
  (extends js/HTMLElement)

  (constructor [this]
               (super)
               (let [shadow (.attachShadow this #js {:mode "open"})]
                 (set! shadow -innerHTML
                       (str
                        (html [:style styles])
                        (html [:header [:h1 "NACE code finder"]])))))

  Object
  (connectedCallback [this]
                     (js/console.log "bm-header" this)))

(when-not (js/window.customElements.get "bm-header")
  (js/window.customElements.define "bm-header" Header))
