import React from 'react';

import {
  ButtonToolbar,
  Selected,
} from '../AMUIReact';

export class SelectedDemo extends React.Component {
  render() {
    var data = [
      {value: 'one', label: 'One'},
      {value: 'two', label: 'Two'},
      {value: 'three', label: 'Three'}
    ];

    var props = {
      data: data,
      onChange: function(value) {
        console.log('当前值为：', value);
      }
    };

    var options = [
      {value: 'one', label: 'One'},
      {value: 'two', label: 'Two'},
      {value: 'three', label: 'Three'},
      {value: 'a', label: 'Apple'},
      {value: 'b', label: 'Banana'},
      {value: 'o', label: 'Orange'},
      {value: 'm', label: 'Mango'}
    ];

    var props2 = {
      data: options,
      onChange: function(value) {
        console.log('当前值为：', value);
      },
      multiple: true,
      maxHeight: 150,
      btnStyle: 'secondary'
    };

    return (
      <div>
        <h2>Selected</h2>
        <h3>单选</h3>
        <ButtonToolbar>
          <Selected {...props} />

          {/* 设置默认值 */ ' '}
          <Selected {...props} btnStyle="primary" value="one" />
        </ButtonToolbar>

        <h3>多选</h3>

        <Selected {...props2} />

        {/* 设置默认值 */' '}
        <Selected {...props2} btnStyle="warning" value="one,o" />
      </div>
    );
  }
}
