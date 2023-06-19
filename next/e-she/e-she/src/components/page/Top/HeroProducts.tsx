import { ProductItem } from '@/components/model/Product/ProductItem'

export const HeroProducts = () => {
  const dummy =
    'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.'
  return (
    <>
      <main>
        <section>
          <ProductItem text={dummy} />
          <ProductItem text={dummy} />
          <ProductItem text={dummy} />
          <ProductItem text={dummy} />
        </section>
      </main>
    </>
  )
}
