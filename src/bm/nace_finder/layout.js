// function tag (tag, ...args) {
//   if (args.length === 2) {
//     return _tag(tag, ...args)
//   } else if (args.length === 1) && typeof args[0] === "object" {
//     return _tag(tag, args[0])
//   } else {
//     return _tag(tag, {}, args[0])
//   }
// }

// function _tag (tag, opts, content = null) {
//   const element = document.createElement(tag)
//   Object.entries(opts).forEach(([ key, value ]) => element[key] = value)

//   if (typeof content === "string") {
//     element.innerHTML = content
//   } else if (content.nodeName) {
//     element.appendChild(content)
//   }

//   return element
// }

// class Layout extends HTMLElement {
//   constructor() {
//     super()
//     this.appendChild(this.style)
//     this.appendChild(this.screen)
//     this.appendChild(this.header)
//     this.appendChild(this.main)
//     this.appendChild(this.footer)
//   }

//   get style() {
//     return this._style ||= tag("link", {rel: "stylesheet", href: "/css/layout.css"})
//   }

//   get screen() {
//     return this._screen ||= tag("div", {id: "screen"})
//   }

//   get header() {
//     return this._header ||= tag("bm-header")
//   }

//   get main() {
//     return this._main ||= tag("main", tag("slot"))
//   }

//   get footer() {
//     return this._footer ||= tag("bm-footer")
//   }
// }
