(ns bm.web-components.utils
  (:require [shadow.cljs.modern :refer (defclass)]))

(defn- set-attrs [element map]
  (reduce-kv (fn [element key value]
               (.setAttribute element key value))
             element map)
  element)

(defn tag
  ([tag-name]
   (js/document.createElement (name tag-name)))

  ([tag-name arg]
   (let [element (js/document.createElement (name tag-name))]
     (cond
       (map? arg) (set-attrs element arg)
       (string? arg) (do (set! element -innerHTML arg) element)
       ; Otherwise assume nested element.
       true (do (.appendChild element arg) element)))))

(defn append-child [instance element]
  (.appendChild (.-shadowRoot instance) element))

(defn attach-shadow [instance]
  (.attachShadow instance #js {:mode "open"}))

(defn event-listener [element event-name handler]
  (.addEventListener element (name event-name) handler))

(defn register-custom-element [tag-name class]
  (when-not (js/window.customElements.get (name tag-name))
    (js/window.customElements.define (name tag-name) class)))

(defn shadow-root [instance]
  (.-shadowRoot instance))

(defclass BMElement
  (extends js/HTMLElement)

  (constructor [this]
               (super)
               (attach-shadow this))

  Object
  (connectedCallback [this] (js/console.log :connected this))
  (disconnectedCallback [this] (js/console.log :disconnected this))
  (adoptedCallback [this] (js/console.log :adopted this))
  (attributeChangedCallback [this attr old-val new-val]
                            (js/console.log :attr-changed this
                                            {:attr attr :old-val old-val :new-val new-val})))

(def BMElement BMElement)
