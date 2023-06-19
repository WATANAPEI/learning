import Link from 'next/link'
import Image from 'next/image'
import Logo from '/public/logo.png'
import { HeaderLink } from './HeaderLink'

export const Header: React.FC = () => {
  return (
    <header>
      <nav>
        <Link href="/">
          <Image src={Logo} alt="Logo image" />
        </Link>
        <ul>
          <li>
            <HeaderLink href="/">Top</HeaderLink>
          </li>
          <li>
            <HeaderLink href="/products">Products</HeaderLink>
          </li>

          <li>
            <HeaderLink href="/cart">Cart</HeaderLink>
          </li>
          <li>
            <a href="#">Dropdown Menu Item</a>
            <ul>
              <li>
                <a href="#">Sublink with a long name</a>
              </li>
              <li>
                <a href="#">Short sublink</a>
              </li>
            </ul>
          </li>
        </ul>
      </nav>
    </header>
  )
}
