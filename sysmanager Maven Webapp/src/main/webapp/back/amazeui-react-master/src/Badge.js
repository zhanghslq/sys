'use strict';

var React = require('react');
var classNames = require('classnames');
var omit = require('object.omit');
var ClassNameMixin = require('./mixins/ClassNameMixin');

var Badge = React.createClass({
  mixins: [ClassNameMixin],

  propTypes: {
    classPrefix: React.PropTypes.string,
    component: React.PropTypes.node,
    href: React.PropTypes.string,
    round: React.PropTypes.bool,
    radius: React.PropTypes.bool,
    amStyle: React.PropTypes.string
  },

  getDefaultProps: function () {
    return {
      classPrefix: 'badge',
      component: 'span'
    };
  },

  renderAnchor: function (classSet) {
    var Component = this.props.component || 'a';
    var href = this.props.href || '#';
    var restProps = omit(this.props, Object.keys(this.constructor.propTypes));

    return (
      <Component
        {...restProps}
        href={href}
        className={classNames(classSet, this.props.className)}
        role="badge"
      >
        {this.props.children}
      </Component>
    );
  },

  render: function () {
    var classSet = this.getClassSet();
    var Component = this.props.component;
    var renderAnchor = this.props.href;
    var restProps = omit(this.props, Object.keys(this.constructor.propTypes));

    if (renderAnchor) {
      return this.renderAnchor(classSet);
    }

    return (
      <Component
        {...restProps}
        className={classNames(classSet, this.props.className)}
      >
        {this.props.children}
      </Component>
    );
  }
});

module.exports = Badge;
