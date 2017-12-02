'use strict';

var React = require('react');
var classNames = require('classnames');
var omit = require('object.omit');
var ClassNameMixin = require('./mixins/ClassNameMixin');

var Form = React.createClass({
  mixins: [ClassNameMixin],

  propTypes: {
    classPrefix: React.PropTypes.string.isRequired,
    horizontal: React.PropTypes.bool,
    inline: React.PropTypes.bool
  },

  getDefaultProps: function() {
    return {
      classPrefix: 'form'
    };
  },

  render: function() {
    var inline = this.props.inline;
    var restProps = omit(this.props, Object.keys(this.constructor.propTypes));
    var classSet = {};

    // remove .am-form className if `inline` prop set
    if (inline) {
      classSet[this.prefixClass('inline')] = true;
    } else {
      classSet = this.getClassSet();
      classSet[this.prefixClass('horizontal')] = this.props.horizontal;
    }

    return (
      <form
        {...restProps}
        className={classNames(classSet, this.props.className)}
      >
        {this.props.children}
      </form>
    );
  }
});

module.exports = Form;
