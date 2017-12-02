'use strict';

var React = require('react');
var classNames = require('classnames');
var omit = require('object.omit');
var ClassNameMixin = require('./mixins/ClassNameMixin');

var Progress = React.createClass({
  mixins: [ClassNameMixin],

  propTypes: {
    classPrefix: React.PropTypes.string,
    now: React.PropTypes.number,
    label: React.PropTypes.string,
    active: React.PropTypes.bool,
    striped: React.PropTypes.bool,
    amSize: React.PropTypes.string,
    amStyle: React.PropTypes.string
  },

  getDefaultProps: function() {
    return {
      classPrefix: 'progress'
    };
  },

  renderProgressBar: function() {
    var styleSheet = {
      width: this.props.now + '%'
    };
    var classes = {};
    var prefix = this.prefixClass('bar');
    var amStyle = this.props.amStyle;

    // set am-progress-bar
    classes[prefix] = true;

    if (amStyle) {
      classes[prefix + '-' + amStyle] = true;
    }

    return (
      <div
        className={classNames(classes)}
        style={styleSheet}
        role="progressbar"
      >
        {this.props.label}
      </div>
    );
  },

  renderChildBar: function(child, index) {
    return React.cloneElement(child, {
      isChild: true,
      key: child.key ? child.key : index
    });
  },

  render: function() {
    var classes = this.getClassSet();
    var restProps = omit(this.props, Object.keys(this.constructor.propTypes));

    // set class
    classes[this.prefixClass('striped')] = this.props.striped;

    if (this.props.active) {
      classes[this.prefixClass('striped')] = true;
    }

    if (!this.props.children) {
      if (!this.props.isChild) {
        return (
          <div
            {...restProps}
            className={classNames(classes, this.props.className)}
          >
            {this.renderProgressBar()}
          </div>
        );
      } else {
        return (
          this.renderProgressBar()
        );
      }
    } else {
      return (
        <div
          {...restProps}
          className={classNames(classes, this.props.className)}
        >
          {React.Children.map(this.props.children, this.renderChildBar)}
        </div>
      );
    }
  }
});

module.exports = Progress;

// Todo: 删除无用 class
//     : key ref 处理问题
