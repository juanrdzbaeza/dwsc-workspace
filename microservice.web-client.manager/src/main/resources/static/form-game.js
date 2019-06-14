import { LitElement, html, customElement, property } from "./node_modules/lit-element/lit-element.js";
export class FormGame extends LitElement {
  static get properties() {
    return {
      title: {
        type: String
      },
      name: {
        type: String
      },
      description: {
        type: String
      },
      cover_url: {
        type: String
      },
      button: {
        type: String
      },
      click_function: {
        type: String
      }
    };
  }

  constructor() {
    super();
    this.title = '';
    this.name = '';
    this.description = '';
    this.cover_url = '';
    this.button = '';
    this.click_function = '';
  }

  render() {
    return html`
    <div style="text-align:center">
      <h1>${this.title}</h1>
      <hr style="width: 30%">
      <br>
      <div>
        <label for="name">Name: </label>
        <input type="text" name="name" id="name" value="${this.name}">
      </div>
      <br>
      <div>
        <label for="description">Description: </label>
        <input type="text" name="description" id="description" value="${this.description}">
      </div>
      <br>
      <div>
        <label for="cover_url">Cover URL: </label>
        <input type="text" name="cover_url" id="cover_url" value="${this.cover_url}">
      </div>
      <br>
      <br>
      <button type="button" onclick="${this.click_function}">${this.button}</button>
    </div>
    `;
  }

}
window.customElements.define('form-game', FormGame);