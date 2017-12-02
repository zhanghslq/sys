import React from 'react';

import * as components from './demos';

export class ComponentDemo extends React.Component {
  render() {
    const component = this.props.params.component;
    const Component = components[component] || React.createClass({
          render() {
            return (
              <h3>
                Not found.
              </h3>
            );
          }
        });
    return <Component />;
  }
}
