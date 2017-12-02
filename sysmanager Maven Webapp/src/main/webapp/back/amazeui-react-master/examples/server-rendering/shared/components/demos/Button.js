import React from 'react';

import {
  Button,
  ButtonToolbar,
} from '../AMUIReact';

export class ButtonDemo extends React.Component {
  render() {
    return (
      <div>
        <h2>按钮</h2>
        <h3>默认样式</h3>
        <ButtonToolbar>
          <Button>Default</Button>
          <Button amStyle="primary">Primary</Button>
          <Button amStyle="secondary">Secondary</Button>
          <Button amStyle="success">Success</Button>
          <Button amStyle="warning">Warning</Button>
          <Button amStyle="danger">Danger</Button>
          <Button amStyle="link">Link</Button>
        </ButtonToolbar>

        <h3>
          圆角
        </h3>
        <ButtonToolbar>
          <Button radius>Default</Button>
          <Button amStyle="primary" radius>Primary</Button>
          <Button amStyle="secondary" radius>Secondary</Button>
          <Button amStyle="success" radius>Success</Button>
          <Button amStyle="warning" radius>Warning</Button>
          <Button amStyle="danger" radius>Danger</Button>
        </ButtonToolbar>

        <h3>椭圆</h3>
        <ButtonToolbar>
          <Button round>Default</Button>
          <Button amStyle="primary" round>Primary</Button>
          <Button amStyle="secondary" round>Secondary</Button>
          <Button amStyle="success" round>Success</Button>
          <Button amStyle="warning" round>Warning</Button>
          <Button amStyle="danger" round>Danger</Button>
        </ButtonToolbar>
      </div>
    );
  }
}
