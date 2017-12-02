import React from 'react';
import PageContainer from '../components/PageContainer';

const About = React.createClass({
  render() {
    return (
      <PageContainer
        {...this.props}
      >
        <h2>系统信息</h2>
        <hr />
        <p>只是一个 Amaze UI React 后台模板骨架。欢迎完善。</p>
      </PageContainer>
    );
  }
});

export default About;
