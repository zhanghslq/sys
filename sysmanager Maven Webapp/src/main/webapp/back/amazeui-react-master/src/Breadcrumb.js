'use strict';

var React = require('react');
var classNames = require('classnames');
var assign = require('object-assign');
var omit = require('object.omit');
var ClassNameMixin = require('./mixins/ClassNameMixin');

var Breadcrumb = React.createClass({
  mixins: [ClassNameMixin],

  propTypes: {
    classPrefix: React.PropTypes.string,
    component: React.PropTypes.node.isRequired,
    slash: React.PropTypes.bool
  },

  getDefaultProps: function() {
    return {
      classPrefix: 'breadcrumb',
      component: 'ul'
    };
  },

  render: function() {
    var classes = this.getClassSet();
    var Component = this.props.component;
    var restProps = omit(this.props, Object.keys(this.constructor.propTypes));

    classes[this.prefixClass('slash')] = this.props.slash;

    return (
      <Component
        {...restProps}
        className={classNames(classes, this.props.className)}
      >
        {this.props.children}
      </Component>
    );
  }
});

Breadcrumb.Item = React.createClass({
  mixins: [ClassNameMixin],

  propTypes: {
    active: React.PropTypes.bool,
    href: React.PropTypes.string,
    title: React.PropTypes.string,
    target: React.PropTypes.string,
    component: React.PropTypes.any,
    linkComponent: React.PropTypes.any,
    linkProps: React.PropTypes.object
  },

  getDefaultProps() {
    return {
      component: 'li'
    };
  },

  renderAnchor: function(classes) {
    var Component = this.props.component;
    var linkComponent = this.props.linkComponent || 'a';
    var restProps = omit(this.props, Object.keys(this.constructor.propTypes));

    return (
      <Component
        {...restProps}
        className={classes}
      >
        {
          React.createElement(
            linkComponent,
            assign({
              href: this.props.href,
              title: this.props.title,
              target: this.props.target
            }, this.props.linkProps),
            this.props.children
          )
        }
      </Component>
    );
  },

  render: function() {
    var classes = classNames(this.props.className);
    var Component = this.props.component;
    var restProps = omit(this.props, Object.keys(this.constructor.propTypes));

    if (this.props.href || this.props.linkComponent) {
      return this.renderAnchor(classes);
    }

    return (
      <Component
        {...restProps}
        className={classes}
      >
        {this.props.children}
      </Component>
    );
  }
});

module.exports = Breadcrumb;
