'use strict';

var React = require('react');
var classNames = require('classnames');
var omit = require('object.omit');
var ClassNameMixin = require('./mixins/ClassNameMixin');

var Grid = React.createClass({
  mixins: [ClassNameMixin],

  propTypes: {
    classPrefix: React.PropTypes.string.isRequired,
    component: React.PropTypes.node.isRequired,
    collapse: React.PropTypes.bool,
    fixed: React.PropTypes.bool
  },

  getDefaultProps: function() {
    return {
      classPrefix: 'g',
      component: 'div'
    };
  },

  render: function() {
    var Component = this.props.component;
    var classSet = this.getClassSet();
    var props = this.props;
    var restProps = omit(this.props, Object.keys(this.constructor.propTypes));

    // .am-g-fixed
    classSet[this.prefixClass('fixed')] = props.fixed;

    // .am-g-collapse
    classSet[this.prefixClass('collapse')] = props.collapse;

    return (
      <Component
        {...restProps}
        className={classNames(this.props.className, classSet)}
      >
        {this.props.children}
      </Component>
    );
  }
});

module.exports = Grid;
