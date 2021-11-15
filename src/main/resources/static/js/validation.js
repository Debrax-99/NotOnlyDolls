(function () {
  var Manager, toArray, bind, debounce, deleteNode;

  // INITIALIZATION

  Manager = function (form) {
    this.form = form;
    this.submit = document.querySelector(
      "#" + form.getAttribute("id") + ' button[type="submit"]'
    );

    this.bindEvents(form.getElementsByTagName("input"));
    this.bindEvents(form.getElementsByTagName("textarea"));
    this.submit.addEventListener("click", bind(this.onSubmit, this));

    this.errorMessages = {
      required: "Este campo es requerido",
      minlength: "La longitud mínima es de $$$",
      maxlength: "La longitud máxima es de $$$",
    };
  };

  // EVENTS

  Manager.prototype.bindEvents = function (htmlCollection) {
    var inputs = toArray(htmlCollection);

    inputs.forEach(function (element) {
      element.addEventListener("change", bind(this.onChange, this));
      element.addEventListener(
        "keyup",
        debounce(bind(this.onChange, this), 500)
      );
    }, this);
  };

  Manager.prototype.onChange = function (evt) {
    this.validate(evt.target);
    this.updateSubmit();
  };

  Manager.prototype.onSubmit = function (evt) {
    var isValid = this.validateForm();

    if (!isValid) {
      evt.preventDefault();
      this.updateSubmit();
    }
  };

  // VALIDATION

  Manager.prototype.validate = function (element) {
    var className = element.className.replace(/is-(in)?valid/g, ""),
      type = element.getAttribute("type"),
      feedback = element.parentNode.getElementsByClassName("invalid-feedback"),
      isValid;

    if (feedback.length > 0) {
      deleteNode(feedback[0]);
    }

    if (type === "text") {
      isValid = this.validateTextField(element);
    } else if (type === "checkbox") {
      isValid = this.validateCheckboxField(element);
    } else if (type === "file") {
      isValid = this.validateFileField(element);
    } else {
      // textarea element
      isValid = this.validateTextField(element);
    }

    if (isValid) {
      className += " is-valid";
    } else {
      className += " is-invalid";
    }
    element.className = className;

    return isValid;
  };

  Manager.prototype.validateTextField = function (element) {
    var isValid = true;

    if (element.hasAttribute("required")) {
      isValid = isValid && element.value.length > 0;
      if (!isValid) {
        this.renderError(element, "required");
      }
    }
    if (element.hasAttribute("maxlength")) {
      isValid =
        isValid &&
        element.value.length <= parseInt(element.getAttribute("maxlength"), 10);
      if (!isValid) {
        this.renderError(element, "maxlength");
      }
    }
    if (element.hasAttribute("minlength")) {
      isValid =
        isValid &&
        element.value.length >= parseInt(element.getAttribute("minlength"), 10);
      if (!isValid) {
        this.renderError(element, "minlength");
      }
    }

    return isValid;
  };

  Manager.prototype.validateCheckboxField = function (element) {
    if (element.hasAttribute("required")) {
      if (!element.checked) {
        this.renderError(element, "required");
      }
      return element.checked;
    }
    return true;
  };

  Manager.prototype.validateFileField = function (element) {
    if (element.hasAttribute("required")) {
      var isValid = element.value.length > 0;
      if (!isValid) {
        this.renderError(element, "required");
      }
      return isValid;
    }
    return true;
  };

  Manager.prototype.updateSubmit = function () {
    var isValid = this.form.getElementsByClassName("is-invalid").length === 0;

    if (isValid) {
      this.submit.removeAttribute("disabled");
    } else {
      this.submit.setAttribute("disabled", "disabled");
    }
  };

  Manager.prototype.renderError = function (element, error) {
    var container = element.parentNode,
      feedback = document.createElement("div"),
      message = this.errorMessages[error];

    if (error === "minlength") {
      message = message.replace("$$$", element.getAttribute("minlength"));
    } else if (error === "maxlength") {
      message = message.replace("$$$", element.getAttribute("maxlength"));
    }

    feedback.className = "invalid-feedback";
    feedback.innerText = message;
    container.appendChild(feedback);
  };

  // ENTRY POINTS

  Manager.prototype.validateForm = function () {
    var isValid = true;

    toArray(this.form.getElementsByTagName("input")).forEach(function (
      element
    ) {
      isValid = this.validate(element) && isValid;
    },
    this);

    toArray(this.form.getElementsByTagName("textarea")).forEach(function (
      element
    ) {
      isValid = this.validate(element) && isValid;
    },
    this);

    this.updateSubmit();

    return isValid;
  };

  window.FormValidation = Manager;

  // UTILS

  toArray = function (arrayLike) {
    if (Array.from) {
      return Array.from(arrayLike);
    }
    // IE support
    return [].slice.call(arrayLike);
  };

  bind = function (func, context) {
    return function () {
      return func.apply(context, arguments);
    };
  };

  debounce = function (func, wait, immediate) {
    var timeout;

    return function () {
      var context = this,
        args = arguments,
        later = function () {
          timeout = null;
          if (!immediate) {
            func.apply(context, args);
          }
        },
        callNow = immediate && !timeout;

      clearTimeout(timeout);
      timeout = setTimeout(later, wait);

      if (callNow) {
        func.apply(context, args);
      }
    };
  };

  deleteNode = function (node) {
    if (node.remove) {
      node.remove();
    } else {
      // IE support
      node.parentNode.removeChild(node);
    }
  };
})();
