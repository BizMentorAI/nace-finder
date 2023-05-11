(ns bm.nace-finder.layout.footer
  (:require-macros [hiccups.core :as hiccups :refer (html)])
  (:require [shadow.cljs.modern :refer (defclass)]
            [hiccups.runtime :as hiccupsrt]))

(def styles "
  footer {
    padding: 20px 0 30px 0;
    text-align: center;
  }
")


(defclass Footer
  (extends js/HTMLElement)

  (constructor [this]
               (super)
               (let [shadow (.attachShadow this #js {:mode "open"})]
                 (set! shadow -innerHTML
                       (str
                        (html [:style styles])
                        (html [:footer [:div "2023"]]))))))

(when-not (js/window.customElements.get "bm-footer")
  (js/window.customElements.define "bm-footer" Footer))
