'use strict';

import React from 'react'
import {
  Link,
} from 'react-router';

import {
  Topbar,
  Nav,
  NavItem,
  CollapsibleNav,
  Container,
  GoTop,
} from './AMUIReact';

import {SiteFooter} from './index';
import * as demos from './demos';

export class App extends React.Component {
  constructor(props, context) {
    super(props);
  }

  renderDemoNav() {
    return (
      <Nav
        className="demo-nav"
        pills
      >
        {
          Object.keys(demos).map((demo, index) => {
            return (
              <NavItem
                key={index}
                linkComponent={Link}
                linkProps={{to: `/${demo}`}}
                active = {this.context.router.isActive(`/${demo}`)}
              >
                {demo.charAt(0).toUpperCase() + demo.substring(1)}
              </NavItem>
            )
          })
        }
      </Nav>
    )
  }

  render() {
    return (
      <div className="ask-page">
        <Topbar
          className="ask-header"
          brand="Amaze UI React Server Rendering"
          brandLink="/"
          inverse
        >
          <CollapsibleNav>
            <Nav topbar>
            </Nav>
          </CollapsibleNav>
        </Topbar>
        <Container className="ask-main">
          <p className="am-margin-top-lg">
            Amaze UI React 组件后端渲染测试
          </p>
          {this.renderDemoNav()}
          <div className="demo-content">
            {this.props.children}
          </div>
        </Container>
        <SiteFooter />
        <GoTop theme="fixed" autoHide icon="arrow-up" />
      </div>
    );
  }
}

App.contextTypes = {
  router: React.PropTypes.object.isRequired
};
