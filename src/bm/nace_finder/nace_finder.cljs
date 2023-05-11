(ns bm.nace-finder.nace-finder
  (:require-macros [hiccups.core :as hiccups :refer (html)])
  (:require [shadow.cljs.modern :refer (defclass)]
            [hiccups.runtime :as hiccupsrt]))

(def styles "
  p:first-of-type {
    color: darkslategrey;
  }

  .number {
    color: lightgreen;
  }

  .text {
    padding-left: 10px;
    font-size: 14pt;
  }
")

(defclass NaceFinder
  (extends js/HTMLElement)

  (constructor [this]
               (super)
               (let [shadow (.attachShadow this #js {:mode "open"})]
                 (set! shadow -innerHTML
                       (str
                        (html [:style styles])
                        (html [:p "NACE Codes are a pan-European classification system that groups organisations according to the general nature of their business activities. They are used for statistical purposes by the Central Statistics Office www.cso.ie and the EU."])

                        (html [:h1
                               [:span.number "1"]
                               [:span.text "Type or select the main business activity"]])

                        ; TODO: autocomplete.
                        (html [:h1
                               [:span.number "2"]
                               [:span.text "Type a product keyword to find the product class"]])

                        ; TODO: autocomplete.
                        (html [:h1
                               [:span.number "3"]
                               [:span.text "Your NACE code is"]]
                               ; TODO: display NACE code.
                              )))))

  Object
  (connectedCallback [this]
                     (js/console.log "bm-nace-finder" this)))

(when-not (js/window.customElements.get "bm-nace-finder")
  (js/window.customElements.define "bm-nace-finder" NaceFinder))
