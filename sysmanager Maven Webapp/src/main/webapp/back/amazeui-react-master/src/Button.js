'use strict';

var React = require('react');
var classNames = require('classnames');
var ClassNameMixin = require('./mixins/ClassNameMixin');
var omit = require('object.omit');

var Button = React.createClass({
  mixins: [ClassNameMixin],

  propTypes: {
    classPrefix: React.PropTypes.string.isRequired,
    active: React.PropTypes.bool,
    block: React.PropTypes.bool,
    disabled: React.PropTypes.bool,
    radius: React.PropTypes.bool,
    round: React.PropTypes.bool,
    component: React.PropTypes.node,
    href: React.PropTypes.string,
    target: React.PropTypes.string,
    type: React.PropTypes.string,
    amSize: React.PropTypes.string,
    amStyle: React.PropTypes.string
  },

  getDefaultProps: function() {
    return {
      classPrefix: 'btn',
      type: 'button',
      amStyle: 'default'
    };
  },

  removeUnknownProps() {
    return omit(this.props, [
      'classPrefix',
      'active',
      'block',
      'radius',
      'round',
      'component',
      'amSize',
      'amStyle'
    ]);
  },

  renderAnchor: function(classSet) {
    var Component = this.props.component || 'a';
    var href = this.props.href || '#';
    var restProps = this.removeUnknownProps();

    return (
      <Component
        {...restProps}
        href={href}
        target={this.props.target}
        className={classNames(this.props.className, classSet)}
        role="button"
      >
        {this.props.children}
      </Component>
    );
  },

  renderButton: function(classSet) {
    var Component = this.props.component || 'button';
    var restProps = this.removeUnknownProps();

    return (
      <Component
        {...restProps}
        className={classNames(this.props.className, classSet)}
      >
        {this.props.children}
      </Component>
    );
  },

  render: function() {
    var classSet = this.getClassSet();
    var renderType = this.props.href || this.props.target ?
      'renderAnchor' : 'renderButton';

    // block button
    classSet[this.prefixClass('block')] = this.props.block;

    return this[renderType](classSet);
  }
});

module.exports = Button;
