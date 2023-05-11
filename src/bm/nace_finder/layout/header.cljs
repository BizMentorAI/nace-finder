(ns bm.nace-finder.layout.header
  (:require-macros [hiccups.core :as hiccups :refer (html)])
  (:require [shadow.cljs.modern :refer (defclass)]
            [hiccups.runtime :as hiccupsrt]))

(def styles "
  header {
    padding: 20px 0;
    text-align: center;
  }

  header h1 {
    margin-top: 0;
  }

  h1, ul {
    text-transform: uppercase;
  }

  ul {
    display: flex;
    list-style: none;
    align-items: center;
    justify-content: center;
    padding-left: 0;
  }

  li {
  }

  li + li::before {
    content: \" | \";
    padding: 12px;
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
                        (html [:header
                               [:h1 "NACE lookup"]
                               [:ul
                                [:li "fast"]
                                [:li "easy"]
                                [:li "accurate"]]])))))

  Object
  (connectedCallback [this]
                     (js/console.log "bm-header" this)))

(when-not (js/window.customElements.get "bm-header")
  (js/window.customElements.define "bm-header" Header))
