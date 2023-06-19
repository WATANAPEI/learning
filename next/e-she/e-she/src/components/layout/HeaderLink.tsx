import Link from 'next/link'
import { useRouter } from 'next/router'
import { ReactNode } from 'react'

type HeaderLinkProps = {
  href: string
  children: ReactNode
}

export const HeaderLink: React.FC<HeaderLinkProps> = ({ href, children }) => {
  const router = useRouter()
  const style = {
    color: router.asPath === href ? 'black' : 'blue'
  }
  return (
    <Link href={href} style={style}>
      {children}
    </Link>
  )
}
