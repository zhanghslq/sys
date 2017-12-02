'use strict';

var React = require('react');
var classNames = require('classnames');
var omit = require('object.omit');
var ClassNameMixin = require('./mixins/ClassNameMixin');

var List = React.createClass({
  mixins: [ClassNameMixin],

  propTypes: {
    classPrefix: React.PropTypes.string,
    component: React.PropTypes.node.isRequired,
    border: React.PropTypes.bool,
    bordered: React.PropTypes.bool,
    striped: React.PropTypes.bool,
    static: React.PropTypes.bool
  },

  getDefaultProps: function() {
    return {
      classPrefix: 'list',
      component: 'ul'
    };
  },

  render: function() {
    var classes = this.getClassSet();
    var Component = this.props.component;
    var props = this.props;
    var prefixClass = this.prefixClass;
    var restProps = omit(this.props, Object.keys(this.constructor.propTypes));

    // am-list-border
    classes[prefixClass('border')] = props.border || props.bordered;

    // am-list-striped
    classes[prefixClass('striped')] = props.striped;

    // am-list-static
    classes[prefixClass('static')] = props.static;

    return (
      <Component
        {...restProps}
        className={classNames(classes, props.className)}
      >
        {props.children}
      </Component>
    );
  }
});

module.exports = List;
