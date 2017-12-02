import React from 'react';

import {
  Slider,
} from '../AMUIReact';

export class SliderDemo extends React.Component {
  render() {
    var onSelect = function(index, direction) {
      console.log('激活的幻灯片编号：', index, '，滚动方向：', direction);
    };

    return (
      <div>
        <h2>Slider</h2>
        <h3>常规模式</h3>
        <Slider onSelect={onSelect}>
          <Slider.Item>
            <img
              src="http://s.amazeui.org/media/i/demos/bing-1.jpg"/>
          </Slider.Item>
          <Slider.Item><img
            src="http://s.amazeui.org/media/i/demos/bing-2.jpg"/></Slider.Item>
          <Slider.Item>
            <img
              src="http://s.amazeui.org/media/i/demos/bing-3.jpg"/></Slider.Item>
          <Slider.Item>
            <img
              src="http://s.amazeui.org/media/i/demos/bing-4.jpg"/></Slider.Item>
        </Slider>
      </div>
    );
  }
}
