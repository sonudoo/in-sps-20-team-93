import React from 'react';
import { render } from '@testing-library/react';
import GetPathComponent from './GetPathComponent';

global.fetch = jest.fn(() =>
  Promise.resolve({
    json: () => Promise.resolve({}),
  })
);

test('renders <GetPathComponent /> with Path Information Success Message', () => {
  const { getByTitle } = render(<GetPathComponent />);

  const linkElement = getByTitle('Path Info Success');

  expect(linkElement).toBeInTheDocument();
});

test('renders <GetPathComponent /> with Path Information Processing Message', () => {
  const { getByTitle } = render(<GetPathComponent />);

  const linkElement = getByTitle('Path Info Processing');

  expect(linkElement).toBeInTheDocument();
});

test('renders <GetPathComponent /> with Previous Path Button', () => {
  render(<GetPathComponent />);

  const linkElement = document.getElementById('PreviousPathButton');

  expect(linkElement).toBeInTheDocument();
});

test('renders <GetPathComponent /> with Next Path Button', () => {
  render(<GetPathComponent />);

  const linkElement = document.getElementById('NextPathButton');

  expect(linkElement).toBeInTheDocument();
});