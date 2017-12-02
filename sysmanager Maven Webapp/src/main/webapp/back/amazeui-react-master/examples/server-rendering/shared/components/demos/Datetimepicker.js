import React from 'react';

import {
  DateTimeInput,
} from '../AMUIReact';

export class DatetimepickerDemo extends React.Component {
  render() {
    return (
      <div>
        <h2>Datetimepicker</h2>
        <h3>基本样式</h3>
        <DateTimeInput dateTime="2015-05-20 12:12" />
      </div>
    );
  }
}
